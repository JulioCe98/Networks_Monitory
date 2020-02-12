import React, { Component } from "react";
import axios from "axios";

export default class Price extends Component {
  constructor(props) {
    super(props);
    this.state = {
      name: this.props.name,
      price: null,
      show: false
    };
    this.ShowPrice = this.ShowPrice.bind(this);
  }

  ShowPrice() {
    this.setState(prevState => ({
      show: !prevState.check
    }));
    axios.get(`https://api.coindesk.com/v1/bpi/currentprice.json`).then(res => {
      const actual = res.data;
      this.setState({ price: actual });
    });
  }

  render() {
    const show = this.state.show;
    let retorno = "";

    if (show) {
      retorno = (
        <div>
          <h1>price : {this.state.price}</h1>
        </div>
      );
    } else {
      retorno = (
        <div>
          <div>
            <h1>Hola {this.state.name}</h1>
            <h1>Actualmente no hay niguna información acerca del precio</h1>
          </div>
        </div>
      );
    }

    return (
      <div>
        <button onClick={this.ShowPrice}>Hacer get</button>
        {retorno}
      </div>
    );
  }
}
