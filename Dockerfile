FROM node:17-alpine as nodework
WORKDIR /app
COPY ./package.json /app/
RUN npm install
COPY . .
RUN npm run build

FROM nginx:1.19.0
WORKDIR /usr/share/nginx/html
RUN rm -rf ./*
COPY --from=nodework /app/build .
ENTRYPOINT [ "nginx","-g","daemon off;"]