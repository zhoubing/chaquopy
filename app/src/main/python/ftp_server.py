from pyftpdlib.authorizers import DummyAuthorizer
from pyftpdlib.handlers import FTPHandler
from pyftpdlib.servers import FTPServer


def startServer():
    authorizer = DummyAuthorizer()
    authorizer.add_user('root', 'root', '/data/data/com.hifly.chaquopy/cache/chaquopy/tmp', perm='elradfmw')

    handler = FTPHandler
    handler.authorizer = authorizer

    server = FTPServer(('0.0.0.0', 5005), handler)
    server.serve_forever()