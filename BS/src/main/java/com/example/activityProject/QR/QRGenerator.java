package com.example.activityProject.QR;

import com.example.activityProject.DTO.UserDto;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRGenerator {
    public static void generateQR(UserDto userDto) throws WriterException, IOException {
        String url="http://localhost:8090";
        String QRPath="C:\\QRCode";
        String QRName=QRPath+"\\"+userDto.getUsername()+".png";
        QRCodeWriter qrCodeWriter=new QRCodeWriter();
        BitMatrix bitMatrix=qrCodeWriter.encode(url+"/"+userDto.getId(), BarcodeFormat.QR_CODE,500,500);
        Path path= FileSystems.getDefault().getPath(QRName);
        MatrixToImageWriter.writeToPath(bitMatrix,"PNG",path);




    }
}
