const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = app => {
    app.use(
        "/api",
        createProxyMiddleware ({
            target: "http://poll-backend:8080/api",
            changeOrigin: true
        })
    );
};