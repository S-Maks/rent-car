package com.car.rent.service.impl;

import com.car.rent.model.Car;
import com.car.rent.model.Client;
import com.car.rent.model.Contract;
import com.car.rent.model.enums.Status;
import com.car.rent.repository.CarRepository;
import com.car.rent.repository.ClientRepository;
import com.car.rent.repository.ContractRepository;
import com.car.rent.service.ContractService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;

    @Override
    public void save(String start, String end, String carId) {
        Car car = carRepository.findById(Long.parseLong(carId)).get();
        Client client = clientRepository.findFirstByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        Contract contract = Contract.builder()
                .startDate(LocalDateTime.parse(start))
                .endDate(LocalDateTime.parse(end))
                .carId(car)
                .clientId(client)
                .status(Status.REVIEW)
                .build();
        contractRepository.save(contract);
    }

    @Override
    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    @Override
    public List<Contract> findByStatus(String status) {
        Optional<Client> client = clientRepository.findFirstByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (client.isPresent()) {
            if (status.equals("")) {
                return contractRepository.findAllByStatusAndClientId(Status.REVIEW, client.get());
            } else {
                return contractRepository.findAllByStatusAndClientId(Status.valueOf(status), client.get());
            }
        } else {
            if (status.equals("")) {
                return contractRepository.findAllByStatus(Status.REVIEW);
            } else {
                return contractRepository.findAllByStatus(Status.valueOf(status));
            }
        }
    }

    @Override
    public List<Contract> findByUser() {
        Client client = clientRepository.findFirstByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        return contractRepository.findAllByClientId(client);
    }

    @Override
    public void approved(Long id) {
        Contract contract = contractRepository.findById(id).get();
        contract.setStatus(Status.APPROVED);
        contractRepository.save(contract);
    }

    @Override
    public void blocked(Long id) {
        Contract contract = contractRepository.findById(id).get();
        contract.setStatus(Status.BLOCKED);
        contractRepository.save(contract);
    }

    @Override
    public void saveFile(Long id, HttpServletResponse response) {
        try {
            File file = File.createTempFile("contract", ".pdf");
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            BaseFont bf = BaseFont.createFont("C:\\Windows\\Fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(bf, 14, Font.NORMAL);
            PdfWriter.getInstance(document, new FileOutputStream(file));

            document.open();
            document.add(new Paragraph("Продам гараж", font));

            document.close();

            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "filename=\"" + file.getName() + "\"");
            FileUtils.copyFile(file, response.getOutputStream());
            file.delete();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }
}
