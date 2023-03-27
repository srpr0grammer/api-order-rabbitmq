package com.alanjsantos.orderapi.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseOrdersDTO {

    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @NotNull(message = "Date order cannot be null")
    private LocalDateTime dateOrder;
    private List<ResponseProductDTO> products;
    @NotBlank(message = "Status order cannot be blank")
    private String statusOrder;
}
