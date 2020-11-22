package com.arcnotes.controller;

import com.arcnotes.controller.request.BackupRequest;
import com.arcnotes.controller.response.BackupResponse;
import com.arcnotes.controller.response.RestoreResponse;
import com.arcnotes.entity.Note;
import com.arcnotes.entity.Snapshot;
import com.arcnotes.entity.User;
import com.arcnotes.service.NoteService;
import com.arcnotes.util.Utils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NoteController {

    private final NoteService noteService;

    @PostMapping("backup")
    @PreAuthorize("hasAuthority(T(com.arcnotes.security.AppAuthority).USER_WRITE.name())")
    BackupResponse backup(
            @Valid @RequestBody BackupRequest request,
            Authentication authentication
    ) {
        User user = Utils.castAuthToAppUser(authentication).getUser();
        Snapshot snapshot = noteService.backup(request, user);
        return BackupResponse.from(snapshot, user);
    }

    @GetMapping("restore")
    @PreAuthorize("hasAuthority(T(com.arcnotes.security.AppAuthority).USER_READ.name())")
    RestoreResponse restore(
            Authentication authentication
    ) {
        User user = Utils.castAuthToAppUser(authentication).getUser();
        List<Note> foundNotes = noteService.restoreLast(user);
        return RestoreResponse.from(foundNotes);
    }
}
