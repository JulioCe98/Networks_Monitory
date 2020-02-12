import React from 'react';
import logo from './logo.svg';
import './App.css';
import Price from './components/price/price';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <Price name={"Juan"}></Price>
      </header>
    </div>
  );
}

export default App;
