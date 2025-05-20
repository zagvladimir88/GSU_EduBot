package com.zagvladimir.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendDocumentMessage {
    private Long chatId;
    private String fileName;
    private byte[] fileBytes;
}
