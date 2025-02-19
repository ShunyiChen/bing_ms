package com.bing.bing.utils.enums;

import com.bing.common.core.exception.legaldatabase.LegalDatabaseException;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>创建时间: 2023/9/6 </p>
 * 邮箱枚举
 *
 * @author <a href="mailto:carson.ren@cn.ey.com" rel="nofollow">任超</a>
 * @version v1.0
 */
@Slf4j
public enum UserEmailEnum {

  TEST("carson.ren@cn.ey.com;",100),
  /**
   * FIDS邮箱
   */
 FIDS("alfred.tai@hk.ey.com;josiah.kuok@hk.ey.com;tommy.au.yeung@hk.ey.com;won.keum@hk.ey.com", 0),


  /**
   * Legal Team邮箱
   */
 LEGAL_TEAM("Kelvin.Ko@hk.ey.com;Tommy.KF.Chan@hk.ey.com", 99),

  /**
   * 北京leader
   */
  LEADER_BEIJIN("Juliet.Qiu@cn.ey.com;", 1),
  /**
   * 上海leader
   */
  LEADER_SHANGHAI("Jerry-Yh.Chen@cn.ey.com;", 2),
  /**
   * 广州leader
   */
  LEADER_GUANGZHOU("Michael.Chen@cn.ey.com;", 3),
  /**
   * 深圳leader
   */
  LEADER_SHENZHEN("Kerry.Lee@cn.ey.com;", 4),
  /**
   * 大连（GDS）leader
   */
  LEADER_DALIAN_GDS("Charlie.Chi@cn.ey.com;", 5),
  /**
   * 香港leader
   */
  LEADER_HONGKONG("Tom.See@hk.ey.com;", 6),
  /**
   * 天津leader
   */
  LEADER_TIANJIN("Juliet.Qiu@cn.ey.com;", 7),
  /**
   * 青岛leader
   */
  LEADER_QINGDAO("Juliet.Qiu@cn.ey.com;", 8),
  /**
   * 大连沈阳leader
   */
  LEADER_DALIAN_SHENYANG("Juliet.Qiu@cn.ey.com;", 9),
  /**
   * 苏州leader
   */
    LEADER_SUZHOU("Jerry-Yh.Chen@cn.ey.com;", 10),
  /**
   * 杭州leader
   */
  LEADER_HANGZHOU("Jerry-Yh.Chen@cn.ey.com;", 11),
  /**
   * 成都、重庆leader
   */
  LEADER_CHENGDU_CHONGQING("Jerry-Yh.Chen@cn.ey.com;", 12),
  /**
   * 西安leader
   */
  LEADER_XIAN("Jerry-Yh.Chen@cn.ey.com;", 13),
  /**
   * 郑州leader
   */
  LEADER_ZHENGZHOU("Jerry-Yh.Chen@cn.ey.com;", 14),
  /**
   * 昆明leader
   */
  LEADER_KUNMING("Kerry.Lee@cn.ey.com;", 14),
  /**
   * 海口leader
   */
  LEADER_HAIKOU("Michael.Chen@cn.ey.com;", 15),
  /**
   * 南京leader
   */
  LEADER_NANJING("Jerry-Yh.Chen@cn.ey.com;", 16),
  /**
   * 合肥leader
   */
  LEADER_HEFEI("Jerry-Yh.Chen@cn.ey.com;", 17),
  /**
   * 宁波leader
   */
  LEADER_NINGBO("Jerry-Yh.Chen@cn.ey.com;", 18),
  /**
   * 太原leader
   */
  LEADER_TAIYUAN("Juliet.Qiu@cn.ey.com;", 19),
  /**
   * 济南leader
   */
  LEADER_JINAN("Juliet.Qiu@cn.ey.com;", 20),
  /**
   * 厦门leader
   */
  LEADER_XIAMEN("Donny.Xiao@cn.ey.com;", 21),
  /**
   * 长沙leader
   */
  LEADER_CHANGSHA("Kerry.Lee@cn.ey.com;", 22),
  /**
   * MACAU leader
   */
  LEADER_MACAU("Tom.See@hk.ey.com;", 23),
  /**
   * 武汉leader
   */
  LEADER_WUHAN("Jerry-Yh.Chen@cn.ey.com;", 24);


  UserEmailEnum(String title, int value) {
    this.title = title;
    this.value = value;
  }

  public String title() {
    return title;
  }

  public int value() {
    return value;
  }

  private String title;
  private int value;

  /**
   * 根据枚举标题转为对应的枚举值
   *
   * @param title 枚举标题
   * @return StatusModeEnum
   */
  public static UserEmailEnum titleOf(String title) {
    for (UserEmailEnum type : UserEmailEnum.values()) {
      if (type.title.equalsIgnoreCase(title)) {
        return type;
      }
    }

    String messgae = String.format("枚举类型转换失败： title => %s ,目标枚举：UserEmailEnum。", title);
    log.warn(messgae);

    throw new LegalDatabaseException(messgae);
  }

  /**
   * 根据枚举value转为title
   *
   * @param value 枚举标题
   * @return String
   * @throws LegalDatabaseException 当枚举类型转换失败时抛出
   */
  public static String valueOf(Integer value) {
    for (UserEmailEnum type : UserEmailEnum.values()) {
      if (type.value == value) {
        return type.title;
      }
    }

    String messgae = String.format("枚举title获取失败： value => %s ,目标枚举：UserEmailEnum。", value);
    log.warn(messgae);

    throw new LegalDatabaseException(messgae);
  }
}