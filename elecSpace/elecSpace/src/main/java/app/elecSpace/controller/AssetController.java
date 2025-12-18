package app.elecSpace.controller;

import app.elecSpace.entity.Asset;
import app.elecSpace.entity.Category;
import app.elecSpace.repository.CategoryRepo;
import app.elecSpace.service.AssetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
    private final AssetService service;
    private final CategoryRepo categoryRepo;

    public AssetController(AssetService service, CategoryRepo categoryRepo) {
        this.service = service;
        this.categoryRepo = categoryRepo;
    }

    @GetMapping
    public List<Asset> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Asset addAsset(@RequestBody Asset asset) {
        if (asset.getCategory() != null && asset.getCategory().getId() != null) {
            Category cat = categoryRepo.findById(asset.getCategory().getId()).orElseThrow();
            asset.setCategory(cat);
        }
        return service.addAsset(asset);
    }
}
