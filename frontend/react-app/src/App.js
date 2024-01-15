import React from 'react';
import {
    BrowserRouter as Router,
    Route,
    Switch,
} from 'react-router-dom';
import {MantineProvider} from '@mantine/core';
import ScoreBoard from "./pages/score-board/ScoreBoard";
import PageNotFound from "./pages/page-not-found/PageNotFound";
import Header from "./pages/header/Header";
import Footer from "./pages/footer/Footer";

function App() {
    return (
        <MantineProvider>
            <Header/>
            <Router>
                <Switch>
                    <Route path="/api/scoreBoard" exact component={ScoreBoard}/>
                    <Route component={PageNotFound}/>
                </Switch>
            </Router>
            <Footer/>
        </MantineProvider>
    );
}

export default App;