package com.eazybytes.controller;

import com.eazybytes.model.Notice;
import com.eazybytes.repository.NoticeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Tag(
        name = "REST API for Notices Details in Eazybank",
        description = "To Get Notices Details Don't have to have any Role"
)
@RestController
@RequiredArgsConstructor
public class NoticesController {

    private final NoticeRepository noticeRepository;

    @Operation(
            summary = "Fetch notices Details REST API",
            description = "REST API to fetch notices that are valid to current Date that exist in DB"
    )
    @GetMapping("/notices")
    public ResponseEntity<List<Notice>> getNotices() {
        List<Notice> notices = noticeRepository.findAllActiveNotices();
        if (notices != null) {
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                    .body(notices);
        } else {
            return null;
        }
    }

}
