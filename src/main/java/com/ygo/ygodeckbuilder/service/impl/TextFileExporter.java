package com.ygo.ygodeckbuilder.service.impl;

import com.ygo.ygodeckbuilder.service.FileExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class TextFileExporter implements FileExporter {

    private static final String EXPORT_DIRECTORY = "src/main/resources/static/files";

    private final Logger logger = LoggerFactory.getLogger(TextFileExporter.class);

    @Override
    public Path export(String fileContent, String fileName) {
        Path filePath = Paths.get(EXPORT_DIRECTORY, fileName);
        try {
            Files.deleteIfExists(filePath);
            return Files.write(filePath, fileContent.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
