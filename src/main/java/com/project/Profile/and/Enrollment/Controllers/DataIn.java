package com.project.Profile.and.Enrollment.Controllers;

import com.opencsv.CSVReader;
import com.project.Profile.and.Enrollment.Entity.StudentEntity;
import com.project.Profile.and.Enrollment.Service.StudentImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/students")
class DataIn {

    @Autowired
    private StudentImportService studentImportService;

    // Helper to safely get string from record, returning null if index is out of bounds or string is empty
    private String getStringOrNull(String[] record, int index) {
        if (index >= record.length || record[index] == null || record[index].trim().isEmpty()) {
            return null;
        }
        return record[index].trim();
    }

    // Helper to parse date
    private LocalDate parseLocalDate(String dateStr, DateTimeFormatter formatter) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(dateStr.trim(), formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Could not parse date: " + dateStr + " with format " + formatter.toString() + ". Error: " + e.getMessage());
            return null; // Or throw custom exception
        }
    }

    // Helper to parse Short
    private Short parseShort(String shortStr) {
        if (shortStr == null) return null;
        try {
            return Short.parseShort(shortStr);
        } catch (NumberFormatException e) {
            System.err.println("Could not parse short: " + shortStr);
            return null; // Or throw custom exception
        }
    }

    // Helper to parse Integer
    private Integer parseInt(String intStr) {
        if (intStr == null) return null;
        try {
            return Integer.parseInt(intStr);
        } catch (NumberFormatException e) {
            System.err.println("Could not parse integer: " + intStr);
            return null; // Or throw custom exception
        }
    }


    @PostMapping("/import-csv")
    public ResponseEntity<?> bulkImportStudents(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload a CSV file.");
        }

        List<String> successfullyImportedRollNums = new ArrayList<>();
        List<String> failedRollNums = new ArrayList<>();
        // IMPORTANT: Adjust date format to match your CSV!!
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CSVReader csvReader = new CSVReader(reader);
            String[] record;

            String[] header = csvReader.readNext();
            System.out.println("CSV Header: " + Arrays.toString(header)); // Skip header row

            int rowNum = 1; // For error reporting
            while ((record = csvReader.readNext()) != null) {
                rowNum++;
                StudentEntity student = new StudentEntity();
                try {
                    // --- CRITICAL ASSUMPTION: CSV columns are in this specific order ---
                    // Update indices if your CSV column order is different.
                    student.setRollNum(getStringOrNull(record, 0));
                    if (student.getRollNum() == null) {
                        failedRollNums.add("Row " + rowNum + ": Missing roll_num");
                        continue; // Essential field
                    }

                    student.setAddress(getStringOrNull(record, 1));
                    student.setAdharNum(getStringOrNull(record, 2));
                    student.setBloodGroup(getStringOrNull(record, 3));
                    student.setCgpa(getStringOrNull(record, 4));
                    student.setDegree(getStringOrNull(record, 5));
                    student.setDob(parseLocalDate(getStringOrNull(record, 6), dateFormatter));
                    student.setEmail(getStringOrNull(record, 7));
                    student.setFatherName(getStringOrNull(record, 8));
                    student.setFirstGraduate(StudentEntity.fg.valueOf(getStringOrNull(record, 9)));
                    student.setGender(StudentEntity.gen.valueOf(getStringOrNull(record, 10).toLowerCase()));
                    student.setGithubProfile(getStringOrNull(record, 11));
                    student.setGradutaionYear(getStringOrNull(record, 12));
                    student.setHscEndYear(getStringOrNull(record, 13));
                    student.setHscPercentage(getStringOrNull(record, 14));
                    student.setHscSchoolName(getStringOrNull(record, 15));
                    student.setHscStartYear(getStringOrNull(record, 16));
                    student.setHscboardOfEducation(getStringOrNull(record, 17)); // varchar in DB
                    student.setImage(getStringOrNull(record, 18));
                    student.setInstitutionName(getStringOrNull(record, 19));
                    student.setLinkedInProfile(getStringOrNull(record, 20));
                    student.setMotherName(getStringOrNull(record, 21));
                    student.setName(getStringOrNull(record, 22));
                    student.setNationality(getStringOrNull(record, 23));
                    student.setPhoneNum(getStringOrNull(record, 24));
                    student.setProgram(getStringOrNull(record, 25));

                    Integer semester = parseInt(getStringOrNull(record, 26));
                    if (semester == null) {
                        failedRollNums.add(student.getRollNum() + ": Missing semester (required)");
                        continue;
                    }
                    student.setSemester(semester);

                    student.setSslcEndYear(getStringOrNull(record, 27));
                    student.setSslcPercentage(getStringOrNull(record, 28));
                    student.setSslcSchoolName(getStringOrNull(record, 29));
                    student.setSslcStartYear(getStringOrNull(record, 30));
                    student.setSslcboardOfEducation(StudentEntity.sslc.valueOf(getStringOrNull(record, 31)));
                    student.setStartYear(getStringOrNull(record, 32));
                    student.setYear(getStringOrNull(record, 33)); // Assuming this is the last column

                    // Attempt to save
                    if (studentImportService.saveStudent(student)) {
                        successfullyImportedRollNums.add(student.getRollNum());
                    } else {
                        failedRollNums.add(student.getRollNum() + ": DB save failed (check server logs for DataIntegrityViolation or other errors)");
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                    failedRollNums.add("Row " + rowNum + ": Incorrect number of columns.");
                } catch (Exception e) { // Catch any other unexpected errors during record processing
                    String rollNumInError = (record != null && record.length > 0 && record[0] != null) ? record[0] : "Unknown RollNum at row " + rowNum;
                    failedRollNums.add(rollNumInError + ": Error processing record - " + e.getMessage());
                }
            }

            if (failedRollNums.isEmpty() && !successfullyImportedRollNums.isEmpty()) {
                return ResponseEntity.ok("All " + successfullyImportedRollNums.size() + " students imported successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.MULTI_STATUS)
                        .body(String.format("Import process completed. Successful: %d, Failed: %d. Failed roll_nums/rows: %s",
                                successfullyImportedRollNums.size(), failedRollNums.size(), failedRollNums));
            }

        } catch (Exception e) { // Catch-all for other unexpected errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }
}