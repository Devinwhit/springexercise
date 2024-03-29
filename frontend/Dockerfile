#############
### build ###
#############

# base image
FROM node:12.9.1 as build

# set working directory
WORKDIR /app/

# add `/app/node_modules/.bin` to $PATH
ENV PATH /app/node_modules/.bin/:$PATH

# install and cache app dependencies
COPY package.json /app/package.json
RUN npm install
RUN npm install -g @angular/cli@7.3.9

# add app
COPY . /app/

# generate build
RUN ng build --output-path=dist/

############
### prod ###
############

# base image
FROM arm64v8/nginx

# copy artifact build from the 'build environment'
COPY --from=build /app/dist/ /usr/share/nginx/html/

# copy nginx conf file to image
COPY nginx-custom.conf /etc/nginx/conf.d/nginx.conf

# expose port 443
EXPOSE 443

# run nginx
CMD ["nginx", "-g", "daemon off;"]
