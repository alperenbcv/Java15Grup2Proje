package org.example.java15grup2proje.repository;

import org.example.java15grup2proje.entity.MediaFile;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MediaFileRepository extends JpaRepository<MediaFile, String> {
}