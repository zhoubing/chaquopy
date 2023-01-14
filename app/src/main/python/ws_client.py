import websocket
from websocket import WebSocketApp

try:
    import thread
except ImportError:
    import _thread as thread
import time

def on_message(ws, message):
    print("####### on_message #######")
    print("message：%s" % message)

def on_error(ws, error):
    print("####### on_error #######")
    print("error：%s" % error)

def on_close(ws, close_status_code, close_msg):
    print("####### on_close #######")

def on_ping(ws, message):
    print("####### on_ping #######")
    print("ping message：%s" % message)

def on_pong(ws, message):
    print("####### on_pong #######")
    print("pong message：%s" % message)

def on_open(ws):
    print("####### on_open #######")
    ws.send("hello")

def startServer():
    websocket.enableTrace(True)  # 开启运行状态追踪。debug 的时候最好打开他，便于追踪定位问题。

    ws = WebSocketApp("ws://10.0.0.2:8888/",
                           on_open=on_open,
                           on_message=on_message,
                           on_error=on_error,
                           on_close=on_close)
    # self.ws.on_open = self.on_open  # 也可以先创建对象再这样指定回调函数。run_forever 之前指定回调函数即可。
    ws.run_forever()

def hello_world(str):
    print(str)
    if java_callback:
        print(java_callback("async msg from python!!!"))
    return "sync msg from Python!!!!"


def pass_object():
    print("pass_object!!!!")
    if pass_object_callback:
        print(pass_object_callback({
            "name": "Python name111"
        }))
    print("pass_object!!!!")
    return {
        "name": "Python name"
    }
