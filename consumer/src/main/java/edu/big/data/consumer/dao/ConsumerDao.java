package edu.big.data.consumer.dao;

import edu.big.data.consumer.db.ArticleInfoDB;
import edu.big.data.consumer.db.BehaviorDB;
import edu.big.data.consumer.db.UserBasicDB;
import edu.big.data.consumer.db.UserEduDB;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ConsumerDao {

    @Insert("insert into user_basic ( \n" +
            "u_id, \n" +
            "gender, \n" +
            "status, \n" +
            "follow_num, \n" +
            "fans_num, \n" +
            "friend_num \n)" +
            "values ( \n" +
            "#{uId},\n" +
            "#{gender},\n" +
            "#{status},\n" +
            "#{followNum},\n" +
            "#{fansNum},\n" +
            "#{friendNum}\n" +
            ")")
    void insertUserBasic(UserBasicDB userBasicDB);

    @Insert("replace into user_edu (\n" +
            "u_id,\n" +
            "degree,\n" +
            "school_name,\n" +
            "major_str \n" +
            ") values ( \n" +
            "#{uId},\n" +
            "#{degree},\n\n" +
            "#{schoolName},\n" +
            "#{majorStr}\n" +
            ")")
    void insertUserEdu(UserEduDB userEduDB);

    @Insert("replace into article_info(\n" +
            "a_id,\n" +
            "digg_count,\n" +
            "bury_count,\n" +
            "view_count,\n" +
            "comment_count,\n" +
            "type,\n" +
            "status,\n" +
            "is_top\n" +
            ") values (" +
            "#{aId},\n" +
            "#{diggCount},\n" +
            "#{buryCount},\n" +
            "#{viewCount},\n" +
            "#{commentCount},\n" +
            "#{type},\n" +
            "#{status},\n" +
            "#{isTop}\n" +
            ")")
    void insertArticle(ArticleInfoDB userEduDB);

    @Insert("insert into user_behavior(\n" +
            "u_id,\n" +
            "behavior,\n" +
            "a_id,\n" +
            "behavior_time\n" +
            ") values( \n" +
            "#{uId},\n" +
            "#{behavior},\n" +
            "#{aId},\n" +
            "#{behaviorTime}\n" +
            ")")
    void insertBehavior(BehaviorDB behaviorDB);
}
