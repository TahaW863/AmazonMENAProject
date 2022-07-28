
console.log("Hello World");
db.webfluxChatServiceDb.messages.drop();
db.webfluxChatServiceDb.createCollection('messages',{capped:true,size:5000000,max:10000});

console.log("Database initialized");