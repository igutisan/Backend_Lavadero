
package com.salchichon.lavadero.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptSummaryDTO {


    private Long id;
    private String dni;
    private String date;
    private BigDecimal total;



}
