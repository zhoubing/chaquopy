from flask import Flask

def helloworld():
    return "Hello World from Python"


def startServer():
    app = Flask(__name__)
#     app.config['FLASKCODE_RESOURCE_BASEPATH'] = '/sdcard/mavicmax/'

    @app.route('/')
    def hello():
        return "Hello World!"

    #这里debug不能设为True 会崩溃，Chaquopy不支持debug
    app.run(host="0.0.0.0", port=5000, debug=False)