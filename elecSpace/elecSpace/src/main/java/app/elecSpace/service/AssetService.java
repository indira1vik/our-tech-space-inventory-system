package app.elecSpace.service;

import app.elecSpace.entity.Asset;
import app.elecSpace.repository.AssetRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {
    private final AssetRepo repo;

    public AssetService(AssetRepo repo) {
        this.repo = repo;
    }

    public List<Asset> getAll() {
        return repo.findAll();
    }

    public Asset addAsset(Asset asset) {
        return repo.save(asset);
    }
}
