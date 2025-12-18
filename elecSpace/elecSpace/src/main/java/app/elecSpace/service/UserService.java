package app.elecSpace.service;

import app.elecSpace.entity.User;
import app.elecSpace.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    public List<User> getAll() {
        return repo.findAll();
    }

    public User addUser(User user) {
        return repo.save(user);
    }
}
