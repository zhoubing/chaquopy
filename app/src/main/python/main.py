from flask import Flask, request
from geventwebsocket.exceptions import WebSocketError
import json
from geventwebsocket.handler import WebSocketHandler
from gevent.pywsgi import WSGIServer

def helloworld():
    return "Hello World from Python"

app = Flask(__name__)

def startServer():
#     app.config['FLASKCODE_RESOURCE_BASEPATH'] = '/sdcard/mavicmax/'
    #这里debug不能设为True 会崩溃，Chaquopy不支持debug
    app.run(host="0.0.0.0", port=5000, debug=False)

@app.route('/')
def hello():
    return "Hello World!"

@app.route('/websocket')
def handle_websocket():
    try:
        print("handle_websocket")
        user_socket = request.environ.get("wsgi.websocket")  # type:WebSocket
        user_socket.send(json.dumps({"msg": "hello from websocket!"})) # " 服务器信息: " +
    except WebSocketError as e:
        print(e)

def startWebSocket():
    http_server = WSGIServer(('0.0.0.0', 5000), app, handler_class=WebSocketHandler)
    http_server.serve_forever()

startWebSocket()