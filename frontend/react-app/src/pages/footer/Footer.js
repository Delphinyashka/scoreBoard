import {Container, Divider, Grid} from "@mantine/core";
import React from "react";

function Footer() {
    return (
        <Container size="xl">
            <Divider/>
            <Grid>
                <Grid.Col span={12}>
                </Grid.Col>
            </Grid>
        </Container>
    );
}

export default Footer;