package com.rescuefly.repository;
import com.rescuefly.entity.*; import java.util.*; import org.springframework.data.jpa.repository.JpaRepository;
public interface UploadedDocumentRepository extends JpaRepository<UploadedDocument,Long>{List<UploadedDocument> findByOwnerOrderByCreatedAtDesc(User owner);}
