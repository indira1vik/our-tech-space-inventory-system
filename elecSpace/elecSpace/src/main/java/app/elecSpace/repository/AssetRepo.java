package app.elecSpace.repository;

import app.elecSpace.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepo extends JpaRepository<Asset, Long> { }
