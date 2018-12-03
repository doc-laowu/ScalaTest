package AnalizeMovie

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 名字	投票人数	类型	产地	上映时间	时长	年代	评分	首映地点
  *
  * 肖申克的救赎	692795	剧情/犯罪	美国	1994/9/10	142	1994	9.6	多伦多电影节
  *
  * 统计每个评分段的数量  0~4 4~6 6~7 7~8 8~9 9~10
  */

object AnalizeByScore {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("AnalizeByScore").setMaster("local[*]")
    val sc = new SparkContext(conf)

    sc.textFile("C:\\Users\\Admin.DESKTOP-HM7VOBS\\Downloads\\11万豆瓣数据\\douban_movies.txt").map(line=>{
      val strarr = line.split("\t")
      val score:Float = strarr(7).toFloat
      var score_stage:String = ""
      if(score >= 0f && score < 4f){
        score_stage = "0~4"
      }else if(score >= 4f && score < 6f){
        score_stage = "4~6"
      }else if(score >= 6f && score < 7f){
        score_stage = "6~7"
      }else if(score >= 7f && score < 8f){
        score_stage = "7~8"
      }else if(score >= 8f && score < 9f){
        score_stage = "8~9"
      }else if(score >= 9f && score < 10f){
        score_stage = "9~10"
      }
      (score_stage, 1)
    }).reduceByKey(_ + _).foreachPartition(insertMysql)

    sc.stop()
  }

  /**
    * 将结果写入数据库中
    *
    * @param iterator
    */
  def insertMysql(iterator: Iterator[(String, Int)])={
    Class.forName ("com.mysql.jdbc.Driver").newInstance()
    val conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/AnalizeMovie", "root", "123456")
    iterator.foreach(data => {
      val ps = conn.prepareStatement("insert into movienumsByScore(score, number) values (?, ?)")
      ps.setString(1, data._1)
      ps.setInt(2, data._2)
      ps.executeUpdate()
    })
  }
}
