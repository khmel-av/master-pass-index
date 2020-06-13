const dev = {
  originContext:      'http://localhost:8080/api'
};

const prod = {
  originContext:         'https://'
};

// console.log(process.env.NODE_ENV);

export let environment = dev;

if (process.env.NODE_ENV === 'production' || process.env.REACT_APP_ENV === 'production') {
  environment = prod;
}
