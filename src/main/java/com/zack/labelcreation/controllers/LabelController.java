package com.zack.labelcreation.controllers;

import com.zack.labelcreation.models.Label;
import com.zack.labelcreation.models.User;
import com.zack.labelcreation.services.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class LabelController {
    private LabelService labelService;

    @Autowired
    public LabelController(LabelService labelService) {
        this.labelService = labelService;
    }

    @GetMapping(value = "/labels")
    public List<Label> listLabels(Principal principal) {
        return labelService.listByUser(principal.getName());
    }

    @GetMapping(value = "/labels/{labelId}")
    public Label getStay(@PathVariable Long labelId, Principal principal) {
        return labelService.findByIdAndHost(labelId, principal.getName());
    }

    @PostMapping("/labels")
    public void addLabel(
            @RequestParam("name") String name,
            Principal principal) {

        Label label = new Label.Builder()
                .setName(name)
                .setHost(new User.Builder().setUsername(principal.getName()).build())
                .build();
        labelService.add(label);
    }

    @DeleteMapping("/labels/{labelId}")
    public void deleteLabel(@PathVariable Long labelId, Principal principal) {
        labelService.delete(labelId, principal.getName());
    }
}
