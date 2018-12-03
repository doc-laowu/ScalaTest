package AnalizeMovie

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 名字	投票人数	类型	产地	上映时间	时长	年代	评分	首映地点
  *
  * 肖申克的救赎	692795	剧情/犯罪	美国	1994/9/10	142	1994	9.6	多伦多电影节
  *
  * 清洗爬取下来的电影数据,清除掉哪些确实字段的数据。
  *
  */

object CleanMovie {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("CleanMovie")
    val sc = new SparkContext(conf)

    sc.textFile("C:\\Users\\Admin.DESKTOP-HM7VOBS\\Downloads\\11万豆瓣数据\\douban_movies.txt")
        .map(line=>{
          val strarr = line.split("\t")
          if(strarr.length >= 9 &&
            !strarr(0).isEmpty
            && !strarr(1).isEmpty
            && !strarr(2).isEmpty
            && !strarr(3).isEmpty
            && !strarr(4).isEmpty
            && !strarr(5).isEmpty
            && !strarr(6).isEmpty
            && !strarr(7).isEmpty){

            strarr(0)+"\t"+strarr(1)+strarr(2)+"\t"+strarr(3)+"\t"+strarr(4)+
              "\t"+strarr(5)+"\t"+strarr(6)+"\t"+strarr(7)+"\t"+strarr(8)

          }
        }).saveAsTextFile("E:\\IdeaProjects\\ScalaPro\\output")

    sc.stop()
  }
}
