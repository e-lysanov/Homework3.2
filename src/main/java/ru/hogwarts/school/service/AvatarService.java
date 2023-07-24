package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AvatarService {

    @Value("${path.to.avatars.folder}")
    private String avatarsDir;
}
