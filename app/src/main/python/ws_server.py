import asyncio
import websockets

async def echo(websocket, path):
    while True:
        recv_text = await websocket.recv()
        print("recv:", recv_text)
        await websocket.send("ok from chaquopy!!!!")

def startServer():
    loop =  asyncio.new_event_loop()
    asyncio.set_event_loop(loop)
    asyncio.get_event_loop().run_until_complete(websockets.serve(echo, '0.0.0.0', 8765))
    asyncio.get_event_loop().run_forever()
#
#
# startServer()