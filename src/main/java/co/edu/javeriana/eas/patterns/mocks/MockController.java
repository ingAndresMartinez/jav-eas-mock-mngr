package co.edu.javeriana.eas.patterns.mocks;

import co.edu.javeriana.eas.patterns.common.dto.quotation.QuotationDetailDto;
import co.edu.javeriana.eas.patterns.common.dto.quotation.QuotationWrapperDto;
import co.edu.javeriana.eas.patterns.common.dto.quotation.RequestQuotationWrapperDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/mock")
public class MockController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockController.class);

    private RestTemplate restTemplate;

    @PostMapping("la-ferreteria")
    public ResponseEntity<Void> ferreteriaMock(@RequestBody RequestQuotationWrapperDto request) {
        LOGGER.info("INICIA SOLICITUD DE COTIZACION PARA PROVEEDOR FERRETERIA [{}]", request);
        CompletableFuture.runAsync(() -> {
            QuotationWrapperDto quotationWrapperDto = basicQuotation(request);
            quotationWrapperDto.setProviderId(2);
            List<QuotationDetailDto> detailsQuotation = new ArrayList<>();
            double amountTotal;
            request.getDetails().forEach(requestQuotationDetailDto -> {
                BasicPrices.Item itemValue = BasicPrices.ferreteria.stream().filter(item ->
                        item.getItemId() == requestQuotationDetailDto.getProductId()).findFirst().orElse(new BasicPrices.Item(99, 800_000));
                QuotationDetailDto quotationDetailDto = new QuotationDetailDto();
                quotationDetailDto.setProductId(requestQuotationDetailDto.getProductId());
                quotationDetailDto.setQuantity(requestQuotationDetailDto.getQuantity());
                quotationDetailDto.setAmount(requestQuotationDetailDto.getQuantity() * itemValue.getItemPrice());
                detailsQuotation.add(quotationDetailDto);

            });
            quotationWrapperDto.setDetails(detailsQuotation);
            amountTotal = detailsQuotation.stream().mapToDouble(QuotationDetailDto::getAmount).sum();
            quotationWrapperDto.setAmountTotal(amountTotal);
            sendQuotation(quotationWrapperDto);
            LOGGER.info("FINALIZA SOLICITUD DE COTIZACION PARA PROVEEDOR FERRETERIA [{}]", quotationWrapperDto);
        });
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("cable-mio")
    public ResponseEntity<QuotationWrapperDto> cableMock(@RequestBody RequestQuotationWrapperDto request) {
        LOGGER.info("INICIA SOLICITUD DE COTIZACION PARA PROVEEDOR CABLE [{}]", request);
        CompletableFuture.runAsync(() -> {
            QuotationWrapperDto quotationWrapperDto = basicQuotation(request);
            quotationWrapperDto.setProviderId(2);
            List<QuotationDetailDto> detailsQuotation = new ArrayList<>();
            double amountTotal;
            request.getDetails().forEach(requestQuotationDetailDto -> {
                BasicPrices.Item itemValue = BasicPrices.ferreteria.stream().filter(item ->
                        item.getItemId() == requestQuotationDetailDto.getProductId()).findFirst().orElse(new BasicPrices.Item(99, 800_000));
                QuotationDetailDto quotationDetailDto = new QuotationDetailDto();
                quotationDetailDto.setProductId(requestQuotationDetailDto.getProductId());
                quotationDetailDto.setQuantity(requestQuotationDetailDto.getQuantity());
                quotationDetailDto.setAmount(requestQuotationDetailDto.getQuantity() * itemValue.getItemPrice());
                detailsQuotation.add(quotationDetailDto);

            });
            quotationWrapperDto.setDetails(detailsQuotation);
            amountTotal = detailsQuotation.stream().mapToDouble(QuotationDetailDto::getAmount).sum();
            quotationWrapperDto.setAmountTotal(amountTotal);
            sendQuotation(quotationWrapperDto);
            LOGGER.info("FINALIZA SOLICITUD DE COTIZACION PARA PROVEEDOR CABLE [{}]", quotationWrapperDto);
        });
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    private QuotationWrapperDto basicQuotation(RequestQuotationWrapperDto request) {
        QuotationWrapperDto quotationWrapperDto = new QuotationWrapperDto();
        quotationWrapperDto.setPersonId(request.getPersonId());
        quotationWrapperDto.setCategoryId(request.getCategoryId());
        quotationWrapperDto.setCategoryDescription(request.getCategoryDescription());
        quotationWrapperDto.setRequestId(request.getRequestQuotationId());
        return quotationWrapperDto;
    }

    private void sendQuotation(QuotationWrapperDto quotationWrapperDto) {
        restTemplate.postForEntity("http://localhost:7073/quotation", quotationWrapperDto, QuotationWrapperDto.class);
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

}