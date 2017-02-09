package person.louchen.restj.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import person.louchen.restj.model.entity.base.BaseDateDomain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by louchen on 2017/2/8.
 */
@Entity
@Table(name = "t_user")
@Getter
@Setter
public class UserEntity extends BaseDateDomain {

    @Column(name = "loginName", nullable = false, unique = true)
    private String loginName;//登录账号

    @Column(name = "loginPwd", nullable = false)
    private String loginPwd;//密码

    @Column(name = "name", nullable = false)
    private String name;//昵称

    @Column(name = "avatar", length = 1000)
    private String avatar;//头像

    @Column(name = "phone")
    private String phone;//手机号

    @Column(name = "email")
    private String email;//邮箱

    @Column(name = "isdel")
    private boolean isdel;//封号

    @Column(name = "memo")
    private String memo;//备注

}
