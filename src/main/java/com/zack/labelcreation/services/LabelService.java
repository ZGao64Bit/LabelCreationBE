package com.zack.labelcreation.services;

import com.zack.labelcreation.exceptions.LabelNotExistException;
import com.zack.labelcreation.models.*;
import com.zack.labelcreation.repositories.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LabelService {
    private LabelRepository labelRepository;

    @Autowired
    public LabelService(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public List<Label> listByUser(String username) {
        return labelRepository.findByHost(new User.Builder().setUsername(username).build());
    }

    public Label findByIdAndHost(Long stayId, String username) throws LabelNotExistException {
        Label label = labelRepository.findByIdAndHost(stayId, new User.Builder().setUsername(username).build());
        if (label == null) {
            throw new LabelNotExistException("Stay doesn't exist");
        }
        return label;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void add(Label label) {
        labelRepository.save(label);

    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void delete(Long stayId, String username) throws LabelNotExistException {
        Label label = labelRepository.findByIdAndHost(stayId, new User.Builder().setUsername(username).build());
        if (label == null) {
            throw new LabelNotExistException("Stay doesn't exist");
        }

        labelRepository.deleteById(stayId);
    }

}
