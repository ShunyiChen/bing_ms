package com.bing.bing.utils.enums;

import com.bing.common.core.exception.legaldatabase.LegalDatabaseException;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>创建时间: 2023/9/13 </p>
 * 办公地点枚举
 *
 * @author <a href="mailto:carson.ren@cn.ey.com" rel="nofollow">任超</a>
 * @version v1.0
 */
@Slf4j
public enum OfficeEnum {

  /**
   * 重庆
   */
//  CHONGQING("Chongqing", 12),
  CHONGQING("Chongqing", 100),
  /**
   * 北京
   */
  BEIJIN("Beijing", 1),
  /**
   * 上海
   */
  SHANGHAI("Shanghai", 2),
  /**
   * 广州
   */
  GUANGZHOU("Guangzhou", 3),
  /**
   * 深圳
   */
  SHENZHEN("Shenzhen", 4),
  /**
   * 大连（GDS）
   */
  DALIAN_GDS("Dalian", 5),
  /**
   * 香港
   */
  HONGKONG("Hong Kong", 6),
  /**
   * 天津
   */
  TIANJIN("Tianjin", 7),
  /**
   * 青岛
   */
  QINGDAO("Qingdao", 8),
  /**
   * 大连沈阳
   */
  DALIAN_SHENYANG("Shenyang", 9),
  /**
   * 苏州
   */
    SUZHOU("Suzhou", 10),
  /**
   * 杭州
   */
  HANGZHOU("Hangzhou", 11),
  /**
   * 成都、重庆
   */
  CHENGDU("Chengdu", 12),
  /**
   * 西安
   */
  XIAN("Xian", 13),
  /**
   * 郑州
   */
  ZHENGZHOU("Zhengzhou", 14),
  /**
   * 昆明
   */
  KUNMING("Kunming", 14),
  /**
   * 海口
   */
  HAIKOU("Haikou", 15),
  /**
   * 南京
   */
  NANJING("Nanjing", 16),
  /**
   * 合肥
   */
  HEFEI("Hefei", 17),
  /**
   * 宁波
   */
  NINGBO("Ningbo", 18),
  /**
   * 太原
   */
  TAIYUAN("Taiyuan", 19),
  /**
   * 济南
   */
  JINAN("Jinan", 20),
  /**
   * 厦门
   */
  XIAMEN("Xiamen", 21),
  /**
   * 长沙
   */
  CHANGSHA("Changsha", 22),
  /**
   * MACAU 
   */
  MACAU("Macau", 23),
  /**
   * 武汉
   */
  WUHAN("Wuhan", 24),
  /**
   * 前海（深圳）
   */
  QIANHAI("Qianhai", 4);


  OfficeEnum(String title, int value) {
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
   * @throws LegalDatabaseException 当枚举类型转换失败时抛出
   */
  public static OfficeEnum titleOf(String title) {
    for (OfficeEnum type : OfficeEnum.values()) {
      if (type.title.equalsIgnoreCase(title)) {
        return type;
      }
    }

    String messgae = String.format("枚举类型转换失败： title => %s ,目标枚举：OfficeEnum。", title);
    log.warn(messgae);

    throw new LegalDatabaseException(messgae);
  }
}