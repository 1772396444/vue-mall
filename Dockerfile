#node 构建
FROM node:lts-alpine as build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install -g yarn
RUN yarn install
COPY . .
RUN yarn build
#nginx 发布
FROM nginx:stable-alpine as production-stage
COPY config/nginx/nginx.conf /etc/nginx/nginx.conf
COPY --from=build-stage /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
RUN echo 'echo init ok!!'
