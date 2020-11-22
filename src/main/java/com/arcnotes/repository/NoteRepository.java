package com.arcnotes.repository;

import com.arcnotes.entity.Note;
import com.arcnotes.entity.Snapshot;
import com.arcnotes.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends BaseRepository<Note> {

    List<Note> findAllByUserAndSnapshot(User user, Snapshot snapshot);
}
