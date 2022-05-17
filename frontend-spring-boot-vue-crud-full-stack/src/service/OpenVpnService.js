import axios from "axios";

const START_OPENVPN_API_URL = `http://localhost:8080/startOpenVpn`;
const STOP_OPENVPN_API_URL = `http://localhost:8080/stopOpenVpn`;
const EXECUTE_SELECTIOON_URL = "http://localhost:8080/executeSelection";

class OpenVpnService {
    callStartOpenVpn(conversationId) {
        return axios.post(`${START_OPENVPN_API_URL}/${conversationId}`);
    }

    callStopOpenVpn(conversationId) {
        return axios.post(`${STOP_OPENVPN_API_URL}/${conversationId}`);
    }

    callExecuteSelection(conversationId, selectionValue) {
        return axios.post(`${EXECUTE_SELECTIOON_URL}/${conversationId}/${selectionValue}`);
    }
}

export default new OpenVpnService();


