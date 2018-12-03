package ActorTest

import scala.actors.Actor

/**
  * 发送start消息和stop的消息是异步的，但是Actor接收到消息执行的过程是同步的按顺序执行
  */

class MyActor extends Actor {

  override def act(): Unit = {
    while (true) {
      receive {
        case "start" => {
          println("starting ...")
          Thread.sleep(5000)
          println("started")
        }
        case "stop" => {
          println("stopping ...")
          Thread.sleep(5000)
          println("stopped ...")
        }
        case "exit" => {
          print("exit app")
          exit()
        }
      }
    }
  }
}

/**
  *   ！表示发送异步消息，没有返回值
  *   !? 发送同步消息，等待返回值
  *   !! 发送异步消息，返回值是 Future[Any]
  */

object MyActor {
  def main(args: Array[String]) {
    val actor = new MyActor
    actor.start()
    actor ! "start"
    actor ! "stop"
    println("消息发送完成！")
    actor ! "exit"
  }
}
