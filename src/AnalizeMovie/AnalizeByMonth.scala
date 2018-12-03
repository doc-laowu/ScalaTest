package AnalizeMovie

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 名字	投票人数	类型	产地	上映时间	时长	年代	评分	首映地点
  *
  * 父与女	53358	剧情/动画/短片	英国/比利时/荷兰	2001/5/27	60	2001	9.2	美国
  *
  * 统计每个国家的电影数量
  */

object AnalizeByMonth {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("AnalizeByMonth").setMaster("local[*]")
    val sc = new SparkContext(conf)

    sc.textFile("C:\\Users\\Admin.DESKTOP-HM7VOBS\\Downloads\\11万豆瓣数据\\douban_movies.txt").map(line=>{

      val strarr = line.split("\t")
      val montharr = strarr(4).split("/")
      var month = ""
      if(montharr.length >= 3){
        month = montharr(1)
      }
      (month, 1)
    }).reduceByKey(_ + _).filter(!_._1.isEmpty()).filter(_._1.length <= 2).foreachPartition(insertMysql)

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
      val ps = conn.prepareStatement("insert into movienumsByMonth(month, number) values (?, ?)")
      ps.setString(1, data._1)
      ps.setInt(2, data._2)
      ps.executeUpdate()
    })
  }

}
