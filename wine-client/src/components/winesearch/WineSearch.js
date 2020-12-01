import React from 'react';
import './WineSearch.css';
import wineglass from './wineglass.png'
import Configuration from "../Configuration";

class SearchResults extends React.Component {
    render() {
        return (
            <div className="search-results">
                {this.props.results.map((result) => (
                    <div key={result.lotCode} className="search-results-row" onClick={() => window.open("/wine/" + result.lotCode, "_self")}>
                        <table className="search-results-row-table">
                            <tbody>
                                <tr>
                                    <td className="lotcode">{result.lotCode}</td>
                                    <td className="volume-tankcode">{result.volume} L</td>
                                </tr>
                                <tr>
                                    <td className="description">{result.description}</td>
                                    <td className="volume-tankcode">{result.tankCode}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                ))}
            </div>
        );
    }
}

export default class WineSearch extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            results: []
        }
        this.search = this.search.bind(this);
        this.config = new Configuration();
    }

    search(e) {
        if (e.target.value) {
            fetch(encodeURI(this.config.WINE_ENDPOINT + '?lotCodeOrDescription=' + e.target.value))
                .then(res => res.json())
                .then((data) => {
                    this.setState({ results: data })
                })
                .catch(function (error) {
                    alert("Sorry. There was a problem fetching data. We are already fixing the problem.");
                })
        } else {
            this.setState({ results: [] });
        }
    }

    render() {
        return (
            <div className="search">
                <div className="search-header">
                    <div className="search-header-text">Wine Search</div>
                    <div className="search-header-icon">
                        <img src={wineglass} className="search-header-icon-img" alt="Search"/>
                    </div>
                </div>
                <div className="search-field">
                    <input type="search" className="search-field-input" placeholder="Search by lot code or description"
                           onKeyUp={this.search} onChange={this.search}/>
                    <SearchResults results={this.state.results}/>
                </div>
            </div>
        );
    }

}
