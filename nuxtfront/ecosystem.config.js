module.exports = {
    apps: [
      {
        name: 'ariadari_front',
        port: '8084',
        exec_mode: 'cluster',
        instances: 'max',
        script: './.output/server/index.mjs',
        //interpreter: ''
      }
    ]
  }