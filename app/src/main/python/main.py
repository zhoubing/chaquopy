from flask import Flask

app = Flask(__name__)

def main():
    app.run(host='0.0.0.0', port=9000)

def startServer():
    app = Flask(__name__)
#     app.config['FLASKCODE_RESOURCE_BASEPATH'] = '/sdcard/mavicmax/'

    @app.route('/')
    def hello():
        return "Hello World!"

    #这里debug不能设为True 会崩溃，Chaquopy不支持debug
    app.run(host="0.0.0.0", port=5000, debug=False)