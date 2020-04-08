package life.majiang.community.service;

import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 中间层 组装请求
 */
@Service
public class QuestionService {

    // 注入依赖
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public List<QuestionDTO> list() {
        List<Question> questions = questionMapper.list(); // 查询所有question
        List<QuestionDTO> questionDTOList = new ArrayList<>(); // new list
        for (Question question : questions) { // 循环Question对象
            User user = userMapper.findByID(question.getCreator()); // 用getCreator获取当前User 返回User对象
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO); // 该工具类的目的是 question的属性快速复制到 questionDTO
            questionDTO.setUser(user); // 返回的DTO对象 需新建list
            questionDTOList.add(questionDTO);

        }

        return questionDTOList; // 返回的 DTOlist
    }
}
