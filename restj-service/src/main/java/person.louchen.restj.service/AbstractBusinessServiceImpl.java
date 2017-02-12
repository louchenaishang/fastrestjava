package person.louchen.restj.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import person.louchen.restj.model.repository.mysql.DeptMysqlRepository;
import person.louchen.restj.model.repository.mysql.UserMysqlRepository;
import person.louchen.restj.model.repository.redis.DeptRedisRepository;

/**
 * Created by louchen on 16/8/27.
 */
public abstract class AbstractBusinessServiceImpl{

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected UserMysqlRepository userMysqlRepository;

    @Autowired
    protected DeptMysqlRepository deptMysqlRepository;

    @Autowired
    protected DeptRedisRepository deptRedisRepository;

}
