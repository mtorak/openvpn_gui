import axios from "axios";

const START_OPENVPN_API_URL = `http://localhost:8080/startOpenVpn`;
const STOP_OPENVPN_API_URL = `http://localhost:8080/stopOpenVpn`;
const EXECUTE_SELECTIOON_URL = "http://localhost:8080/executeSelection";

class OpenVpnService {
    callStartOpenVpn() {
        return axios.post(START_OPENVPN_API_URL);
    }

    callStopOpenVpn() {
        return axios.post(STOP_OPENVPN_API_URL);
    }

    callExecuteSelection(selectionValue) {
        return axios.post(`${EXECUTE_SELECTIOON_URL}/${selectionValue}`);
    }
}

export default new OpenVpnService();


