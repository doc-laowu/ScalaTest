package AnalizeMovie

import AnalizeMovie.TopTenByScore.insertMysql
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 名字	投票人数	类型	产地	上映时间	时长	年代	评分	首映地点
  *
  * 肖申克的救赎	692795	剧情/犯罪	美国	1994/9/10	142	1994	9.6	多伦多电影节
  *
  * 统计每个国家的电影投票人数前十的电影
  */

object TopTenByVotes {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("AnalizeByCategory").setMaster("local[*]")
    val sc = new SparkContext(conf)

    sc.textFile("C:\\Users\\Admin.DESKTOP-HM7VOBS\\Downloads\\11万豆瓣数据\\douban_movies.txt").map(line=>{

      val strarr = line.split("\t")
      val votes:Int = strarr(1).toInt
      val citys = strarr(3).split("/")
      citys.map(x=>{
        (x, (strarr(0), votes))
      })
    }).flatMap(x=>x).groupByKey().mapValues(x=>{
      //根据分数进行排序
      x.toList.sortBy(_._2).reverse.take(10)

    }).foreachPartition(insertMysql)
      //.foreach(print)

    sc.stop()
  }

  /**
    * 将结果写入数据库中
    *
    * @param iterator
    */
  def insertMysql(iterator: Iterator[(String, List[(String, Int)])])= {
        Class.forName ("com.mysql.jdbc.Driver").newInstance()
        val conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/AnalizeMovie", "root", "123456")

    //    val ps = conn.prepareStatement("create table TopTenByVotes("+
    //      "city varchar(20), moviename varchar(64), votes int)")
    //    ps.execute()

        iterator.foreach(data => {
          data._2.foreach(x=>{
            val ps = conn.prepareStatement("insert into TopTenByVotes(city, moviename, votes) values (?, ?, ?)")
            ps.setString(1, data._1)
            ps.setString(2, x._1)
            ps.setInt(3, x._2)
            ps.executeUpdate()
          })
        })
  }

}
