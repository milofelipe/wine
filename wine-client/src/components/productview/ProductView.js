import React from 'react';
import './ProductView.css';
import close from './Close.png';
import W from './W.png';
import Edit from './Edit.png'
import ArrowDown from './ArrowDown.png'
import Configuration from "../Configuration";

class Breakdown extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            showMore: true
        }
        this.showMore = this.showMore.bind(this);
    }

    showMore(val) {
        this.setState({showMore: val});
    }

    render() {
        let firstFive;
        let showMore;
        let allRecords;
        if (this.props.results.length > 5 && this.state.showMore) {
            firstFive = this.props.results.slice(0, 5).map((result) => (
                    <tr className="product-view-breakdown-table-row">
                        <td className="product-view-breakdown-table-key">{result.key}</td>
                        <td className="product-view-breakdown-table-percentage">{result.percentage}%</td>
                    </tr>
                ))
            showMore = (<tr className="product-view-breakdown-table-row" onClick={() => this.showMore(false)}>
                            <td className="product-view-breakdown-table-showmore" colSpan={2}>Show more <img src={ArrowDown} alt="Arrow Down"/></td>
                        </tr>)
        } else {
            allRecords = this.props.results.map((result) => (
                <tr className="product-view-breakdown-table-row">
                    <td className="product-view-breakdown-table-key">{result.key}</td>
                    <td className="product-view-breakdown-table-percentage">{result.percentage}%</td>
                </tr>
            ))
        }
        return (
            <div>
                <table className="product-view-breakdown-table">
                    <thead className="product-view-breakdown-table-row">
                        <th className="product-view-breakdown-table-key">{this.props.type}</th>
                        <th className="product-view-breakdown-table-percentage">Percentage</th>
                    </thead>
                    <tbody>
                        {allRecords}
                        {firstFive}
                        {showMore}
                    </tbody>
                </table>
            </div>
        );
    }
}

export default class ProductView extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            wine: [],
            breakdown: [],
            breakdownType: 'Year'
        }
        this.breakdown = this.breakdown.bind(this);
        this.breakdownElement = React.createRef();
        this.config = new Configuration();
    }

    breakdown(type, label) {
        fetch(encodeURI(this.config.BREAKDOWN_ENDPOINT + '/' + type + '/' + this.props.match.params.lotCode))
            .then(res => res.json())
            .then((data) => {
                this.setState({breakdown: data.breakdown, breakdownType: label})
            })
            .catch(function (error) {
                console.log("Sorry. There was a problem fetching data. We are already fixing the problem.");
            })
        this.breakdownElement.current.showMore(true);
    }

    componentDidMount() {
        fetch(encodeURI(this.config.WINE_ENDPOINT + '/' + this.props.match.params.lotCode))
            .then(res => res.json())
            .then((data) => {
                this.setState({wine: data})
            })
            .catch(function (error) {
                alert("Sorry. There was a problem fetching data. We are already fixing the problem.");
            })
        this.breakdown('year', 'Year');
    }

    render() {
        return (
            <div className="product-view">
                <div className="product-view-header">
                    <div>
                        <span><a href="/"><img src={close} alt="Close"/></a></span>
                        <span className="product-view-header-edit"><a onClick={() => alert('Edit Clicked!')}><img src={Edit} alt="Edit"/></a></span>
                    </div>
                    <div className="product-view-header-text">
                        <div>
                            <img src={W} className="product-view-header-text-img" alt="W"/>
                        </div>
                        <div>{this.state.wine.lotCode}</div>
                    </div>
                    <div className="product-view-header-description">{this.state.wine.description}</div>
                </div>
                <div className="product-view-summary">
                    <table className="product-view-summary-table">
                        <tbody>
                        <tr className="product-view-summary-row">
                            <td className="product-view-summary-label">Volume</td>
                            <td className="product-view-summary-value">{this.state.wine.volume} L</td>
                        </tr>
                        <tr className="product-view-summary-row">
                            <td className="product-view-summary-label">Tank code</td>
                            <td className="product-view-summary-value">{this.state.wine.tankCode}</td>
                        </tr>
                        <tr className="product-view-summary-row">
                            <td className="product-view-summary-label">Product state</td>
                            <td className="product-view-summary-value">{this.state.wine.productState}</td>
                        </tr>
                        <tr className="product-view-summary-row">
                            <td className="product-view-summary-label">Owner</td>
                            <td className="product-view-summary-value">{this.state.wine.ownerName}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div className="product-view-tabs">
                    <div className={`product-view-tab ${this.state.breakdownType === "Year" ? "product-view-tab-active" : ""} `}
                         onClick={() => {this.breakdown('year', 'Year')}}>Year</div>
                    <div className={`product-view-tab ${this.state.breakdownType === "Variety" ? "product-view-tab-active" : ""} `}
                         onClick={() => {this.breakdown('variety', 'Variety')}}>Variety</div>
                    <div className={`product-view-tab ${this.state.breakdownType === "Region" ? "product-view-tab-active" : ""} `}
                         onClick={() => {this.breakdown('region', 'Region')}}>Region</div>
                    <div className={`product-view-tab ${this.state.breakdownType === "Year & Variety" ? "product-view-tab-active" : ""} `}
                         onClick={() => {this.breakdown('year-variety', 'Year & Variety')}}>Year & Variety</div>
                </div>
                <div className="product-view-breakdown">
                    <Breakdown type={this.state.breakdownType} results={this.state.breakdown} ref={this.breakdownElement}/>
                </div>
            </div>
        );
    }

}
