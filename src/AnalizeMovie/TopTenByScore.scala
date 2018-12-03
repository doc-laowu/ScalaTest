package AnalizeMovie

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 名字	投票人数	类型	产地	上映时间	时长	年代	评分	首映地点
  *
  * 肖申克的救赎	692795	剧情/犯罪	美国	1994/9/10	142	1994	9.6	多伦多电影节
  *
  * 求每个国家的电影的评分的前十的电影名
  *
  * 结果：Array((美国, Array(肖申克的救赎, 控方证人, ...), (美国, Array(肖申克的救赎, 控方证人, ...), ...))
  *
  */

object TopTenByScore {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("AnalizeByCategory").setMaster("local[*]")
    val sc = new SparkContext(conf)

    sc.textFile("C:\\Users\\Admin.DESKTOP-HM7VOBS\\Downloads\\11万豆瓣数据\\douban_movies.txt").map(line=>{

      val strarr = line.split("\t")
      val score:Float = strarr(7).toFloat
      val citys = strarr(3).split("/")
      citys.map(x=>{
        (x, (strarr(0), score))
      })
    }).flatMap(x=>x).groupByKey().mapValues(x=>{
      //根据分数进行排序
      x.toList.sortBy(_._2).reverse.take(10)

    }).filter(_._2.length >= 10)
      .foreachPartition(insertMysql)

    sc.stop()
  }
  /**
    * 将结果写入数据库中
    *
    * @param iterator
    */
  def insertMysql(iterator: Iterator[(String, List[(String, Float)])])={
    Class.forName ("com.mysql.jdbc.Driver").newInstance()
    val conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/AnalizeMovie", "root", "123456")

    iterator.foreach(data => {
        data._2.foreach(x=>{
          val ps = conn.prepareStatement("insert into TopTenBySocre(city, moviename, score) values (?, ?, ?)")
          ps.setString(1, data._1)
          ps.setString(2, x._1)
          ps.setFloat(3, x._2)
          ps.executeUpdate()
        })
    })
  }
}
