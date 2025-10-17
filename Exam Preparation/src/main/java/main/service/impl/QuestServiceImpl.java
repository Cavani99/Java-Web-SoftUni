package main.service.impl;

import main.repository.QuestRepository;
import main.service.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestServiceImpl implements QuestService {

    private QuestRepository questRepository;

    @Autowired
    public QuestServiceImpl(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

}
