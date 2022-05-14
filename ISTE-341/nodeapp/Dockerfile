# click on “tags” and pick a version that contains the latest stable version of
# you can always change your mind if it doesn't work
FROM node:carbon
# Create app directory
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
# Install app dependencies
COPY package.json /usr/src/app/
RUN npm install
# Bundle app source
COPY . /usr/src/app
EXPOSE 3000
CMD [ "node", "index.js" ]